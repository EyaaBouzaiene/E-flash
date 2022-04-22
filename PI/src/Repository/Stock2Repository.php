<?php

namespace App\Repository;

use App\Entity\Stock2;
use App\Entity\Partenaires;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Stock2|null find($id, $lockMode = null, $lockVersion = null)
 * @method Stock2|null findOneBy(array $criteria, array $orderBy = null)
 * @method Stock2[]    findAll()
 * @method Stock2[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class Stock2Repository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Stock2::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Stock2 $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Stock2 $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }



    // /**
    //  * @return Stock2[] Returns an array of Stock2 objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Stock2
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */


   public function findByretourID()
   {
   return $this->getEntityManager()
    
        ->createQuery("SELECT s.id FROM APP\Entity\Stock2 s  WHERE  s.qteS<10")
       
            ->getResult();
   }
   
   public function findByretourMail($id)
   {
  return $this->getEntityManager()
    
        ->createQuery("SELECT p.mailP FROM APP\Entity\Partenaires p WHERE p.id=:id")
       ->setParameter('id',$id)
        ->getResult();
   }


   public function ch(){
   {
    $queryBuilder = $this->getEntityManager()->createQueryBuilder()
    ->select('p', 'p.mailP')
    ->from('APP\Entity\Partenaires', 'p')
    ->Join('APP\Entity\Stock2', 'ppc', 'WITH', 'p.id=ppc.partenaire')

    ->where('ppc.qualiteS<10')  ;
 
    $query = $queryBuilder->getQuery();
    $results = $query->getResult();
    return $results;
 
 
}
   }
}
